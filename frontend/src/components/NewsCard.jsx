import PropTypes from 'prop-types';
import FavoriteButton from "./FavoriteButton.jsx";

function NewsCard({ title, description, url, image, source, publishedAt }) {
    const maxLength = 100;
    const truncatedDescription = (description ?? '').length > maxLength
        ? (description ?? '').substring(0, maxLength) + '...'
        : (description ?? '');

    return (
        <div className="news-card">
            <img src={image} alt="news" className="news-image" />
            <div className="news-content">
                <h3 className="news-title">{title}</h3>
                <p className="news-description">{truncatedDescription}</p>
                <span className="news-date">{source} • {new Date(publishedAt).toLocaleDateString()}</span>
                <a href={url} target="_blank" rel="noopener noreferrer" className="read-more-button">
                    Read more
                </a>
                <FavoriteButton article={{ title, description, image, publishedAt }} />
            </div>
        </div>
    );
}

NewsCard.propTypes = {
    title: PropTypes.string.isRequired,
    description: PropTypes.string,
    url: PropTypes.string.isRequired,
    image: PropTypes.string.isRequired,
    source: PropTypes.string.isRequired,
    publishedAt: PropTypes.string.isRequired,
};

export default NewsCard;