// src/pages/Login.js
import React, { useState } from 'react';
import { TextField, Button, Container, Typography, Box } from '@mui/material';
import api from '../services/api'; // se tiver

export default function Login() {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const res = await api.post('/api/users/login', { email, password: senha });
            console.log('OK:', res.data);
        } catch (err) {
            console.error('Erro:', err);
        }
    };

    return (
        <Container maxWidth="xs" sx={{ mt: 8 }}>
            <Typography variant="h5" align="center" gutterBottom>
                Login
            </Typography>
            <Box component="form" onSubmit={handleSubmit} sx={{ display:'grid', gap:2 }}>
                <TextField
                    label="Email"
                    type="email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    fullWidth
                    required
                />
                <TextField
                    label="Senha"
                    type="password"
                    value={senha}
                    onChange={e => setSenha(e.target.value)}
                    fullWidth
                    required
                />
                <Button type="submit" variant="contained" color="primary">
                    Entrar
                </Button>
            </Box>
        </Container>
    );
}
