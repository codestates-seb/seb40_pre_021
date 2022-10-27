import styled from 'styled-components';
import Header from '../components/Header/Header';
import { ReactComponent as Icon } from '../assets/images/icon.svg';
import OAuths from '../components/OAuths/OAuths';
import LoginForm from '../components/Login/LoginForm';

const LoginStyle = styled.div`
	padding: 24px;
	background-color: rgb(239, 240, 241);
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
`;

const Login = () => {
	return (
		<>
			<Header />
			<LoginStyle>
				<Icon width="40" heigth="40" />
				<OAuths />
				<LoginForm />
			</LoginStyle>
		</>
	);
};

export default Login;
