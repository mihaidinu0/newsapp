import { FaGithub, FaLinkedin } from 'react-icons/fa';

function Footer() {
    return (
        <footer className="footer">
            <p>Created by Mihai Dinu</p>
            <div className="footer-links">
                <a href="https://github.com/mihaidinu0" target="_blank" rel="noopener noreferrer">
                    <FaGithub /> GitHub
                </a>
                <a href="https://www.linkedin.com/in/mihaidinu0" target="_blank" rel="noopener noreferrer">
                    <FaLinkedin /> LinkedIn
                </a>
            </div>
        </footer>
    );
}

export default Footer;