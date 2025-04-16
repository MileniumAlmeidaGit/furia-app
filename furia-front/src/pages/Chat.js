import React, { useState } from 'react';
import {
    Container,
    List,
    ListItem,
    ListItemText,
    TextField,
    Button,
    Box,
    Typography
} from '@mui/material';
import api from '../services/api'; // ou 'axios'

export default function Chat() {
    const [question, setQuestion] = useState('');
    const [messages, setMessages] = useState([]);

    const handleSubmit = async e => {
        e.preventDefault();
        if (!question.trim()) return;
        // adiciona pergunta no histórico
        const newMsg = { question, answer: '...', timestamp: new Date().toLocaleTimeString() };
        setMessages(prev => [...prev, newMsg]);
        setQuestion('');

        // chama o backend
        try {
            const res = await api.post('/api/chatbot', { question });
            setMessages(prev =>
                prev.map(msg =>
                    msg === newMsg ? { ...msg, answer: res.data.answer } : msg
                )
            );
        } catch (err) {
            setMessages(prev =>
                prev.map(msg =>
                    msg === newMsg ? { ...msg, answer: 'Erro ao buscar resposta' } : msg
                )
            );
        }
    };

    return (
        <Container maxWidth="sm" sx={{ mt: 4, display: 'flex', flexDirection: 'column', height: '80vh' }}>
            <Typography variant="h4" gutterBottom>Chatbot</Typography>
            <List sx={{ flexGrow: 1, overflowY: 'auto', mb: 2 }}>
                {messages.map((m, i) => (
                    <ListItem key={i} alignItems="flex-start">
                        <ListItemText
                            primary={<strong>Você:</strong>}
                            secondary={m.question}
                        />
                        <ListItemText
                            primary={<strong>Bot:</strong>}
                            secondary={m.answer}
                        />
                        <Typography variant="caption" sx={{ ml: 2 }}>{m.timestamp}</Typography>
                    </ListItem>
                ))}
            </List>
            <Box component="form" onSubmit={handleSubmit} sx={{ display: 'flex', gap: 1 }}>
                <TextField
                    fullWidth
                    placeholder="Pergunte algo..."
                    value={question}
                    onChange={e => setQuestion(e.target.value)}
                />
                <Button type="submit" variant="contained">Enviar</Button>
            </Box>
        </Container>
    );
}
