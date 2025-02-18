import { initializeApp } from "firebase/app";
import { getAuth, GoogleAuthProvider, signInWithPopup, signOut } from "firebase/auth";
import { getFirestore } from "firebase/firestore";

// 🔥 Your Firebase Config (replace with your own)
const firebaseConfig = {
    apiKey: "AIzaSyBMS0NWp5lAEm_w3Y3O_A0_bvvr6lXPbAc",
    authDomain: "news-aggregator-cb5bf.firebaseapp.com",
    projectId: "news-aggregator-cb5bf",
    storageBucket: "news-aggregator-cb5bf.firebasestorage.app",
    messagingSenderId: "104786765202",
    appId: "1:104786765202:web:6550ee222f2291581e8d4b",
    measurementId: "G-MDG1FK2WH4"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const googleProvider = new GoogleAuthProvider();
const db = getFirestore(app);

const signInWithGoogle = async () => {
    try {
        const result = await signInWithPopup(auth, googleProvider);
        console.log("User:", result.user);
    } catch (error) {
        console.error("Error signing in:", error);
    }
};

const logOut = async () => {
    await signOut(auth);
};

export { auth, signInWithGoogle, logOut, db };