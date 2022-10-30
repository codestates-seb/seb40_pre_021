import styled from 'styled-components';
import Header from '../components/Header/Header';
import { ReactComponent as Icon } from '../assets/images/icon.svg';
import OAuths from '../components/OAuths/OAuths';
import LoginForm from '../components/Login/LoginForm';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useEffect, useState } from 'react';
const LoginStyle = styled.div`
	height: 100vh;
	display: flex;
	flex-direction: column;
`;
const LoginBody = styled.div`
	flex: 1;
	padding: 24px;
	background-color: rgb(239, 240, 241);
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;

	.singup {
		display: flex;
		flex-direction: column;
		align-items: center;

		div {
			font-size: 13px;
			padding: 10px;
		}
		a {
			text-decoration: none;
			color: hsl(206, 100%, 40%);
			font-weight: bold;
		}
	}
`;

const Login = () => {
	const [isLogin, setIsLogin] = useState(false);
	const [userInfo, setUserInfo] = useState(null);

	const authHandler = () => {
		axios
			.get('https://localhost:4000/userinfo')
			.then((res) => {
				setIsLogin(true);
				setUserInfo(res.data);
			})
			.catch((err) => {
				if (err.response.status === 401) {
					console.log(err.response.data);
				}
			});
	};

	useEffect(() => {
		authHandler();
	}, []);

	return (
		<LoginStyle>
			<Header />
			<LoginBody>
				<Icon width="40" heigth="40" />
				<OAuths />
				<LoginForm setIsLogin={setIsLogin} setUserInfo={setUserInfo} />
				<div className="singup">
					<div>
						Don’t have an account? <Link to="/signup">Sign up</Link>
					</div>
					<div>
						Are you an employer? <Link to="">Sign up on Talent</Link>
					</div>
				</div>
			</LoginBody>
		</LoginStyle>
	);
};

export default Login;
