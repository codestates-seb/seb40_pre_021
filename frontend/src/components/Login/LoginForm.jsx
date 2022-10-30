import axios from 'axios';
import { useState } from 'react';
import styled from 'styled-components';
import { Login } from '../../api/LoginApi';
import Button from '../common/Button';
import InputForm from '../common/InputForm';

const LoginFormStyle = styled.div`
	margin-top: 5px;
	margin-bottom: 40px;
	padding: 20px 10px 30px 10px;
	width: 270px;
	border-radius: 5px;
	background-color: white;
	box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
		0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1) !important;
	display: flex;
	flex-direction: column;
	align-items: center;

	button {
		width: 243px;
	}
`;
const LoginForm = ({ setUserInfo, setIsLogin }) => {
	const [loginInfo, setLoginInfo] = useState({
		email: '',
		password: '',
	});
	const [errorMessage, setErrorMessage] = useState('');
	const handleInputValue = (key) => (e) => {
		setLoginInfo({ ...loginInfo, [key]: e.target.value });
	};
	const loginRequestHandler = () => {
		const { email, password } = loginInfo;
		if (!email || !password) {
			setErrorMessage('아이디와 비밀번호를 입력하세요');
			return;
		} else {
			setErrorMessage('');
		}
		//api
		Login(loginInfo).then((res) => {
			setIsLogin(true);
			setUserInfo(res.data);
			navigator('/');
		});
	};
	return (
		<LoginFormStyle>
			<InputForm text="Email" callback={() => handleInputValue('email')} />
			<InputForm
				text="Password"
				blueText="Forgot password?"
				type="password"
				callback={() => handleInputValue('password')}
			/>
			<Button text="Log in" callback={loginRequestHandler} />
		</LoginFormStyle>
	);
};

export default LoginForm;
