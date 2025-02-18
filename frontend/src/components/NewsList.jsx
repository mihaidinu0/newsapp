import NewsCard from "./NewsCard";
import SkeletonLoader from "./SkeletonLoader";

function NewsList({ articles, loading }) {
    return (
        <div className="news-list">
            {loading
                ? Array.from({ length: 10 }).map((_, index) => <SkeletonLoader key={index} />)
                : articles.map((article, index) => <NewsCard key={index} {...article} />)}
        </div>
    );
}

export default NewsList;