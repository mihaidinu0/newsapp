import { useState } from "react";

function Sidebar({ selectedCategory, setSelectedCategory }) {
    const categories = ["business", "entertainment", "general", "health", "science", "sports", "technology"];

    const handleCategoryChange = (category) => {
        setSelectedCategory(category);
    };

    return (
        <aside className="sidebar">
            <h2>Filters</h2>
            {categories.map((category) => (
                <button
                    key={category}
                    className={`category-button ${selectedCategory === category ? 'selected' : ''}`}
                    onClick={() => handleCategoryChange(category)}
                >
                    {category.charAt(0).toUpperCase() + category.slice(1)}
                </button>
            ))}
        </aside>
    );
}

export default Sidebar;