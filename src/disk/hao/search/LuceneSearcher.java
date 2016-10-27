package disk.hao.search;

import disk.hao.entity.SearchResult;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.QueryBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LuceneSearcher {

	private String field;
    private Analyzer analyzer;
    private IndexReader indexReader;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public LuceneSearcher(IndexReader indexReader,String field, Analyzer analyzer ) {
        this.field = field;
        this.analyzer = analyzer;
        this.indexReader=indexReader;
    }

    /**
     * 调用lucene检索 返回
     */
    public ArrayList<SearchResult> luceneSearch(String queryString , int top)
            throws Exception {

        ArrayList<SearchResult> searchResult = new ArrayList<SearchResult>();
        IndexSearcher searcher = new IndexSearcher(indexReader);
        QueryBuilder builder = new QueryBuilder(analyzer);
        Query query = builder.createMinShouldMatchQuery(field, queryString.trim(), 0.0f);

        if(queryString.length()>1){//search(query, top);
            TopDocs docs = searcher.search(query, top);
            //searcher.s
            for (ScoreDoc doc : docs.scoreDocs) {
                int num = doc.doc;// 文档内部编号
                //double score = doc.score;
                Document docu = searcher.doc(num);

                String sq = docu.get("content");
                String sa = docu.get("link");

                sq = lighter(sq,query);

//                System.out.print(sq+"\t");
//                System.out.print(sa+"\t");
//                System.out.println(sd);

//                SearchResult searchere = new SearchResult(sa, sq, sdf.parse(sd));
                SearchResult searchere = new SearchResult(sa, sq);

                searchResult.add(searchere);
            }
        }else{
            System.err.println("null query at:"+queryString);
        }
        return searchResult;
    }

    public String lighter(String re,Query query) throws IOException, InvalidTokenOffsetsException {

        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style=\"color:red\">", "</span>");

        Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query));
        //QueryScorer queryScorer = ;
        //Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
        //highlighter.setTextFragmenter(fragmenter);
        String str = highlighter.getBestFragment(analyzer,field,re);
        return str;
    }

    public static void main(String[] args) throws Exception {

        String queryString = "天使";
        //query=question_analyzer.anaSearch(query);
        //Analyzer analyzer = new SmartChineseAnalyzer();
        //Analyzer analyzer = new SimpleAnalyzer();
        Analyzer analyzer = new StandardAnalyzer();
        Path p = Paths.get("/home/hao/文档/j2ee_workspace/diskSearcher/data/index");
        Directory dir = FSDirectory.open(p);
        IndexReader reader = DirectoryReader.open(dir);

        LuceneSearcher searcher = new LuceneSearcher(reader,"content",analyzer);

        ArrayList<SearchResult> list = searcher.luceneSearch(queryString,10);
//        for (Document doc:list) {
//            String sq = doc.get("content");
//            String sa = doc.get("link");
//            String sd = doc.get("date");
//            System.out.print(sq+"\t");
//            System.out.print(sa+"\t");
//            System.out.println(sd);
//        }
    }
	
	
	
}
