import { useEffect, useState, useCallback } from "react";
import Navbar from "./components/Navbar";
import NewsList from "./components/NewsList";
import Sidebar from "./components/Sidebar";
import Pagination from "./components/Pagination";
import "./App.css";
import Footer from "./components/Footer.jsx";

function App() {
    const [articles, setArticles] = useState([]);
    const [page, setPage] = useState(1);
    const [searchTerm, setSearchTerm] = useState("");
    const [country, setCountry] = useState("us");
    const [selectedCategory, setSelectedCategory] = useState("");
    const [loading, setLoading] = useState(true);

    const fetchArticles = useCallback(() => {
        setLoading(true);
        const query = searchTerm ? `&q=${searchTerm}` : '';
        const category = selectedCategory ? `&category=${selectedCategory}` : '';
        fetch(`http://localhost:8080/api/news/latest?page=${page}&pageSize=10&country=${country}${query}${category}`)
            .then((res) => res.json())
            .then((data) => {
                setArticles(data.articles);
                setLoading(false);
            });
    }, [searchTerm, country, page, selectedCategory]);

    useEffect(() => {
        const debounceFetch = setTimeout(() => {
            fetchArticles();
        }, 500);

        return () => clearTimeout(debounceFetch);
    }, [fetchArticles]);

    return (
        <div className="app-container">
            <Navbar searchTerm={searchTerm} setSearchTerm={setSearchTerm} country={country} setCountry={setCountry} />
            <div className="content">
                <Sidebar selectedCategory={selectedCategory} setSelectedCategory={setSelectedCategory} />
                <NewsList articles={articles} loading={loading} />
            </div>
            <Pagination page={page} setPage={setPage} />
            <Footer />
        </div>
    );
}

export default App;