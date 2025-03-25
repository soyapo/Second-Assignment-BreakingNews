package AP;

import java.util.List;

public class News {
    protected String status;
    protected int totalResults;
    protected List<Article> articles;

    public class Article {
        protected Source source;
        protected String author;
        protected String title;
        protected String description;
        protected String url;
        protected String urlToImage;
        protected String publishedAt;
        protected String content;
    }
    
    public class Source {
        protected String id;
        protected String name;
    }
}

