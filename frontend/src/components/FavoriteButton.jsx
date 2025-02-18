import PropTypes from 'prop-types';
import { useState, useEffect } from "react";
import { db, auth } from "../config/firebase";
import { doc, getDoc, setDoc } from "firebase/firestore";
import { useAuthState } from "react-firebase-hooks/auth";
import { FaHeart, FaRegHeart, FaSpinner } from "react-icons/fa";

function FavoriteButton({ article }) {
    const [user] = useAuthState(auth);
    const [isFavorite, setIsFavorite] = useState(false);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchFavorites = async () => {
            if (user) {
                const userDoc = doc(db, "users", user.uid);
                const docSnap = await getDoc(userDoc);
                if (docSnap.exists()) {
                    const favorites = docSnap.data().favorites || {};
                    setIsFavorite(!!favorites[article.title]);
                }
            }
            setLoading(false);
        };
        fetchFavorites();
    }, [user, article.title]);

    const handleFavorite = async () => {
        if (user) {
            setLoading(true);
            const userDoc = doc(db, "users", user.uid);
            const docSnap = await getDoc(userDoc);
            const favorites = docSnap.exists() ? docSnap.data().favorites || {} : {};
            if (isFavorite) {
                delete favorites[article.title];
            } else {
                favorites[article.title] = article;
            }
            await setDoc(userDoc, { favorites }, { merge: true });
            setIsFavorite(!isFavorite);
            setLoading(false);
        } else {
            alert("Please log in to save favorite articles.");
        }
    };

    return (
        <button className="favorite-button" onClick={handleFavorite} disabled={loading}>
            {loading ? <FaSpinner className="spinner" /> : (isFavorite ? <FaHeart /> : <FaRegHeart />)}
        </button>
    );
}

FavoriteButton.propTypes = {
    article: PropTypes.shape({
        title: PropTypes.string.isRequired,
        description: PropTypes.string,
        image: PropTypes.string,
        publishedAt: PropTypes.string.isRequired,
    }).isRequired,
};

export default FavoriteButton;