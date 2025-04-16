import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import { Link } from 'react-router-dom';

export default function Navbar() {
    return (
        <AppBar position="static" color="primary">
            <Toolbar>
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    FURIA
                </Typography>
                {['/', '/login', '/chat', '/users'].map((path, i) => (
                    <Button
                        key={i}
                        color="inherit"
                        component={Link}
                        to={['/','/login','/chat','/users'][i]}
                    >
                        {['Feed','Login','Chat','Usuários'][i]}
                    </Button>
                ))}
            </Toolbar>
        </AppBar>
    );
}
