import React, { useState } from 'react';
import api from '../services/api';

export default function Chat() {
    const [question, setQuestion] = useState('');
    const [answer, setAnswer] = useState('');

    const handleSend = async () => {
        const res = await api.post('/chatbot', { question });
        setAnswer(res.data.answer);
    };

    return (
        <div style={{ padding: 20 }}>
            <h1>Chat</h1>
            <input
                type="text"
                placeholder="Pergunte algo..."
                value={question}
                onChange={e => setQuestion(e.target.value)}
                style={{ width: '300px', marginRight: 8 }}
            />
            <button onClick={handleSend}>Enviar</button>
            {answer && <p style={{ marginTop: 20 }}>Resposta: {answer}</p>}
        </div>
    );
}
