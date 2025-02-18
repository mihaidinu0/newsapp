function Pagination({ page, setPage }) {
    return (
        <div className="pagination">
            <button className="page-button" disabled={page === 1} onClick={() => setPage(page - 1)}>
                Previous
            </button>
            <span className="page-number">Page {page}</span>
            <button className="page-button" onClick={() => setPage(page + 1)}>Next</button>
        </div>
    );
}

export default Pagination;