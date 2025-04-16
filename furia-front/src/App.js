import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Feed from './pages/Feed';
import Login from './pages/Login';
import Chat from './pages/Chat';
import Users from './pages/Users';

function App() {
    return (
        <Router>
            <Navbar />
            <div className="pt-4"> {/* dá um padding-top caso precise */}
                <Routes>
                    <Route path="/" element={<Feed />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/chat" element={<Chat />} />
                    <Route path="/users" element={<Users />} />
                    <Route path="/" element={<Feed />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
