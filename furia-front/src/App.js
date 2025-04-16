import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Login from './pages/Login';
import Feed from './pages/Feed';
import Chat from './pages/Chat';
import Users from './pages/Users';

function App() {
    return (
        <Router>
            <nav style={{ padding: 10, background: '#222', color: '#fff' }}>
                <Link to="/" style={{ margin: 8, color: '#fff' }}>Feed</Link>
                <Link to="/login" style={{ margin: 8, color: '#fff' }}>Login</Link>
                <Link to="/chat" style={{ margin: 8, color: '#fff' }}>Chat</Link>
                <Link to="/users" style={{ margin: 8, color: '#fff' }}>Usuários</Link>
            </nav>
            <Routes>
                <Route path="/" element={<Feed />} />
                <Route path="/login" element={<Login />} />
                <Route path="/chat" element={<Chat />} />
                <Route path="/users" element={<Users />} />
            </Routes>
        </Router>
    );
}

export default App;
