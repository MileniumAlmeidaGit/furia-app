import React, { useState, useEffect } from 'react';
import {
    Container,
    Typography,
    Grid,
    Card,
    CardContent,
    CardActions,
    Button
} from '@mui/material';
import api from '../services/api';

export default function Feed() {
    const [notifs, setNotifs] = useState([]);
    const [games, setGames] = useState([]);

    useEffect(() => {
        // busca notificações
        api.get('/api/notifications')
            .then(res => setNotifs(res.data))
            .catch(err => console.error(err));
        // busca jogos
        api.get('/api/games')
            .then(res => setGames(res.data))
            .catch(err => console.error(err));
    }, []);

    return (
        <Container sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>Feed Social</Typography>
            <Grid container spacing={2}>
                {/* Cards de Notificações */}
                {notifs.map(n => (
                    <Grid item xs={12} md={6} key={`n-${n.id}`}>
                        <Card variant="outlined">
                            <CardContent>
                                <Typography>{n.content}</Typography>
                                <Typography variant="caption" color="text.secondary">
                                    {new Date(n.timestamp).toLocaleString()}
                                </Typography>
                            </CardContent>
                        </Card>
                    </Grid>
                ))}
                {/* Cards de Jogos */}
                {games.map(g => (
                    <Grid item xs={12} md={6} key={`g-${g.id}`}>
                        <Card variant="outlined">
                            <CardContent>
                                <Typography variant="h6">
                                    {g.homeTeam} x {g.awayTeam}
                                </Typography>
                                <Typography variant="body2">
                                    {new Date(g.gameDate).toLocaleString()}
                                </Typography>
                                <Typography variant="caption" color="text.secondary">
                                    Status: {g.status}
                                </Typography>
                            </CardContent>
                            <CardActions>
                                <Button size="small">Detalhes</Button>
                            </CardActions>
                        </Card>
                    </Grid>
                ))}
                {/* Se não tiver nada */}
                {notifs.length + games.length === 0 && (
                    <Grid item xs={12}>
                        <Typography align="center" color="text.secondary">
                            Sem itens no feed.
                        </Typography>
                    </Grid>
                )}
            </Grid>
        </Container>
    );
}
