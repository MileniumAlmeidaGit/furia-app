import React, { useState, useEffect } from 'react';
import api from '../services/api';

export default function Users() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        api.get('/users')
            .then(res => setUsers(res.data))
            .catch(console.error);
    }, []);

    return (
        <div style={{ padding: 20 }}>
            <h1>Lista de Usuários</h1>
            {users.length === 0
                ? <p>Nenhum usuário.</p>
                : (
                    <ul>
                        {users.map(u => (
                            <li key={u.id}>{u.username} — {u.email}</li>
                        ))}
                    </ul>
                )
            }
        </div>
    );
}
