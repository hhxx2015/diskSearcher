package disk.hao.search;

import disk.hao.config.config;
import disk.hao.entity.SearchResult;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Searcher {
	public ArrayList<SearchResult> search(String queryString) {
		ArrayList<SearchResult>  re = new ArrayList<>();
		try {
			String indexPath = config.lucene_index_path;
			int top=1000;
			String field = "content";
	        Analyzer analyzer = new StandardAnalyzer();
	        
	        Path p = Paths.get(indexPath);
	        Directory dir = FSDirectory.open(p);
			
	        IndexReader reader = DirectoryReader.open(dir);
	        LuceneSearcher searcher = new LuceneSearcher(reader,field,analyzer);
			re = searcher.luceneSearch(queryString,top);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        return re;
	}
	
	
	public static void main(String[] args) throws Exception {
		Searcher a = new Searcher();
		System.out.println(a.search("语文英").size());
	}
	
}
