import { useState } from "react";
import { signInWithGoogle, logOut, auth } from "../config/firebase";
import { onAuthStateChanged } from "firebase/auth";

function AuthButton() {
    const [user, setUser] = useState(null);

    onAuthStateChanged(auth, (currentUser) => {
        setUser(currentUser);
    });

    return (
        <div>
            {user ? (
                <>
                    <p>Welcome, {user.displayName}</p>
                    <button onClick={logOut}>Log Out</button>
                </>
            ) : (
                <button onClick={signInWithGoogle}>Sign in with Google</button>
            )}
        </div>
    );
}

export default AuthButton;
