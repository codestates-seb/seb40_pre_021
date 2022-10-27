import styled from 'styled-components';
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
const LoginForm = () => {
	return (
		<LoginFormStyle>
			<InputForm text="Email" />
			<InputForm text="Password" blueText="Forgot password?" />
			<Button text="Log in" />
		</LoginFormStyle>
	);
};

export default LoginForm;
