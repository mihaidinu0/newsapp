import { Link } from "react-router-dom";
import { auth } from "../config/firebase.js";
import { useAuthState } from "react-firebase-hooks/auth";

function Navbar({ searchTerm, setSearchTerm, country, setCountry }) {
    const [user] = useAuthState(auth);

    return (
        <nav className="navbar">
            <Link to="/" className="logo">NewsApp</Link>
            <input
                type="text"
                placeholder="Search news..."
                className="search-bar"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
            />
            {/*<select value={country} onChange={(e) => setCountry(e.target.value)} className="country-select">*/}
            {/*    <option value="us">US</option>*/}
            {/*    <option value="uk">UK</option>*/}
            {/*    <option value="ro">RO</option>*/}
            {/*</select>*/}
            <div className="nav-links">
                {user ? (
                    <>
                        <Link to="/preferences" className="nav-link">Preferences</Link>
                        <button onClick={() => auth.signOut()} className="nav-button">Logout</button>
                    </>
                ) : (
                    <Link to="/login" className="nav-link">Login</Link>
                )}
            </div>
        </nav>
    );
}

export default Navbar;