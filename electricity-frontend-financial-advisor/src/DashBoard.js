import React, {useEffect} from 'react';
import {sendAuthorizationRequest, sendTokenRequest, sendAuthorizationRequestWithOTP} from "./actions/sign-in";
import {dispatchLogout} from "./actions/sign-out";
import {sendRegistration} from  "./actions/register";
import {isValidSession, getAllSessionParameters, decodeIdToken, initAuthenticatedUserSession, initAuthenticatedRoles, getUserRole} from "./actions/session";
import pkceChallenge from 'pkce-challenge';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import {Container} from "@mui/material";
import Button from '@mui/material/Button';
import Consumer from './Consumer/Consumer';
import FinancialAdvisor from './FinanacialAdvisor/FinancialAdvisor';
import MeterReader from './MeterReader/MeterReader';
import Form from './Form/Form';
import './DashBoard/Dashboard.css';
import axiosConfig from './Config/axiosConfig';
import RoleConstant from './Config/RoleConstant';
import { Grid, Paper } from "@mui/material";
import { ThemeProvider, createTheme } from '@mui/material/styles';

function loginClick() {
    sendAuthorizationRequest();
}

function logOutClick() {
    dispatchLogout();
}

function registerClick(){
    sendRegistration();
}

function getRole(data){
    for (var i =0; i < data.length; i++){
        switch (data) {
            case RoleConstant.consumer:
                break;
            case RoleConstant.meterReader:
                break;
            case RoleConstant.finance:
                break;
            case RoleConstant.admin:
                break;
        }
        return data[i];
    }
}

const drawerWidth = 240;

const darkTheme = createTheme({
    palette: {
        mode: 'dark',
        primary: {
            main: '#784d02',
        },
    },
});

const MainDashBoard = () => {
    const [isLoggedIn, setIsLoggedIn] = React.useState(false);
    const [idToken, setIdToken] = React.useState({});
    const [tokenResponse, setTokenResponse] = React.useState({});

    useEffect(() => {
        if (isValidSession()) {
            const session = getAllSessionParameters();
            const _tokenResponse = {
                access_token: session.ACCESS_TOKEN,
                refresh_token: session.REFRESH_TOKEN,
                scope: session.SCOPE,
                id_token: session.ID_TOKEN,
                token_type: session.TOKEN_TYPE,
                expires_in: parseInt(session.EXPIRES_IN),
            };
            setTokenResponse(_tokenResponse);
            setIdToken(decodeIdToken(session.ID_TOKEN));
            setIsLoggedIn(true);
            return;
        }

        const code = new URL(window.location.href).searchParams.get("code");

        if (code) {
            sendTokenRequest(code)
                .then(response => {
                    console.log("TOKEN REQUEST SUCCESS", response);
                    setTokenResponse(response[0]);
                    setIdToken(response[1]);
                    setIsLoggedIn(true);
                    initAuthenticatedRoles(response[1].groups);
                    var jsonData = {
                        "id": "",
                        "username": response[1].sub,
                        "firstName": response[1].given_name,
                        "lastName": response[1].family_name,
                        "email": response[1].email,
                        "mobile": response[1].phone_number,
                        "roleName" : getRole(response[1].groups)
                    };
                    axiosConfig.post(process.env.REACT_APP_APIM_USERS_PREFIX + process.env.REACT_APP_API_PREFIX + '/users', jsonData)
                        .then(function (response) {
                            axiosConfig.get(process.env.REACT_APP_APIM_USERS_PREFIX + process.env.REACT_APP_API_PREFIX + '/users', {params: {username: jsonData.username}})
                                .then(function (response) {
                                    localStorage.setItem('username', response.data.username);
                                    initAuthenticatedUserSession(response.data);
                                    window.location.replace('/');
                                })
                                .catch(function (error) {
                                    console.log(error);
                                });
                        })
                        .catch(function (error) {
                            alert(error);
                        });
                })
                .catch((error => {
                    console.log("TOKEN REQUEST ERROR", error);
                    setIsLoggedIn(false);
                }));
        }
    }, []);

    let role  = getUserRole();
    const formParam = window.location.pathname;

    let formButton;
    let form = false;
    if(role === RoleConstant.finance) {
        if(formParam !== '/form'){
            form = true;
            formButton = <Button color="inherit" onClick={event => window.location.href = '/form'}>Register</Button>;
        }
    } else {
        form = false;
    }

    return (
        <BrowserRouter>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <ThemeProvider theme={darkTheme}>
                <AppBar
                    position="fixed"
                    color="primary"
                    enableColorOnDark
                >
                    <Toolbar>
                        <Typography
                            component="h1"
                            variant="h6"
                            color="inherit"
                            noWrap
                            sx={{flexGrow: 1}}
                        >
                            Reporting Dashboard
                        </Typography>
                        {isLoggedIn ? <>
                            {formButton}
                            <Button color="inherit" onClick={() => {logOutClick()}}>Logout</Button>
                        </> : <></>}
                    </Toolbar>
                </AppBar>
                </ThemeProvider>
            </Box>
            <Box
                component="nav"
                sx={{
                    backgroundColor: (theme) =>
                        theme.palette.mode === 'light'
                            ? theme.palette.grey[100]
                            : theme.palette.grey[900],
                    flexGrow: 1,
                    width: {sm: drawerWidth},
                    height: '9vh',
                    flexShrink: {sm: 0},
                    overflow: 'auto'
                }}
                aria-label="mailbox folders"
            >
            </Box>
            <Box
                component="main"
                sx={{
                    backgroundColor: (theme) =>
                        theme.palette.mode === 'light'
                            ? theme.palette.grey[100]
                            : theme.palette.grey[900],
                    flexGrow: 1,
                    height: '100vh',
                    overflow: 'auto',
                }}
            >
                <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                    <Grid container spacing={3}>
                        <Grid item xs={12} md={5} lg={4}></Grid>
                        {isLoggedIn ? <>
                        </> : <>
                            <Grid item xs={12} md={5} lg={4}>
                                <Paper
                                    sx={{
                                        p: 2,
                                        display: 'flex',
                                        flexDirection: 'column',
                                        height: 240,
                                    }}
                                >
                                    <Button color="inherit" onClick={() => {
                                        loginClick()
                                    }}>Login</Button>
                                </Paper>
                            </Grid>
                        </>}
                        <Grid item xs={12} md={5} lg={4}></Grid>
                    </Grid>
                    {form? <>
                        <FinancialAdvisor/>
                    </> : <></>}
                    <Routes>
                        <Route path="form" element={<Form/>}/>
                    </Routes>
                </Container>
            </Box>
        </BrowserRouter>
    );
}

export default MainDashBoard;