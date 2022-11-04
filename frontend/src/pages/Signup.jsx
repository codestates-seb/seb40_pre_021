import styled from 'styled-components';
import Header from '../components/Header/Header';
import OAuths from '../components/OAuths/OAuths';
import SignupForm from '../components/Signup/SignForm';
import { Link, useNavigate } from 'react-router-dom';
import SignupGuide from '../components/Signup/SignupGuide';
import useDynamicTitle from '../hooks/useDynamicTitle';
import { useSelector } from 'react-redux';
import { selectIsLogin } from '../modules/userReducer';
import { useEffect } from 'react';
const SignupStyle = styled.div`
	display: flex;
	flex-direction: column;
`;
const SignupBody = styled.div`
	padding: 24px;
	background-color: rgb(239, 240, 241);
	display: float;
	justify-content: center;
	align-items: center;

	.signup_form {
		margin-left: 30px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
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

const Signup = () => {
	const navigate = useNavigate();
	const isLogin = useSelector(selectIsLogin);
	useEffect(() => {
		if (isLogin) {
			navigate('/', { replace: true });
		}
	}, []);
	useDynamicTitle('Sign Up - Stack Overflow');
	return (
		<SignupStyle>
			<Header />
			<SignupBody>
				<SignupGuide />
				<div className="signup_form">
					<OAuths />
					{/*SignupForm 안에 회원가입 통신 기능 있음*/}
					<SignupForm />
					<div className="singup">
						<div>
							Already have an account? <Link to="/login">Log in</Link>
						</div>
						<div>
							Are you an employer? <Link to="">Sign up on Talent</Link>
						</div>
					</div>
				</div>
			</SignupBody>
		</SignupStyle>
	);
};

export default Signup;
