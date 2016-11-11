package disk.hao.search;

import disk.hao.config.config;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;


/**
 * Created by hao on 16-8-31.
 * @since Java1.6+
 */

public class BuildIndex {
    //创建本地句子索引�?
    int nub = 0;
    public void buildNewIndex(String fileDir,String sentenceIndex) throws Exception {
        HashSet<String> setname = new HashSet<>();
        Path p = Paths.get(sentenceIndex);
        Directory dir =  new SimpleFSDirectory(p);//判断索引文件是否存在

        //Analyzer analyzer=new SimpleAnalyzer();
        //Analyzer analyzer=new SmartChineseAnalyzer(true);
        Analyzer analyzer=new StandardAnalyzer();
        IndexWriterConfig con = new IndexWriterConfig(analyzer);

        IndexWriter indexWriter = new IndexWriter(dir, con);

        File fs[] = new File(fileDir).listFiles();
        for(File f:fs){
            BufferedReader br = new BufferedReader(new FileReader(f));
            br.readLine();

            FieldType fieldType = new FieldType();
            fieldType.setStored(true);//set 是否存储
            fieldType.setTokenized(true);//是否分词
            fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);

            FieldType fieldlink = new FieldType();
            fieldlink.setTokenized(false);//是否分词
            fieldlink.setStored(true);
            fieldlink.setIndexOptions(IndexOptions.NONE);

            String line="";
            while((line=br.readLine())!=null){
                Document sentence=new Document();

//                System.out.println(line);

                String ls[] = line.split("\t");
                if(!ls[0].equals("null")){
                    if(ls.length>2){
                        if(setname.contains(ls[1])){

                        }else{
                            String link_str ="https://pan.baidu.com/s/"+ls[0];
//                    System.out.println(ls[1]);
                            sentence.add(new Field("content",ls[1],fieldType));
                            sentence.add(new Field("link",link_str,fieldlink));
//                sentence.add(new Field("date",br.readLine(),fieldlink));
                            indexWriter.addDocument(sentence);
                            nub++;
                            setname.add(ls[1]);
                        }

                    }
                }

            }
            br.close();
        }
        indexWriter.close();
        System.out.println(nub);

    }

    public static void main(String[] args) throws Exception {
        BuildIndex bi = new BuildIndex();
        String pathFile = config.disk_message_path;
        String indexPath = config.lucene_index_path;
        bi.buildNewIndex(pathFile,indexPath);
    }
}
