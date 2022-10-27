import styled from 'styled-components';
import Button from '../common/Button';
import InputForm from '../common/InputForm';

const SignupFormStyle = styled.div`
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
const Guide = styled.div`
	width: 90%;
	color: gray;
	font-size: 12px;
	font-weight: bold;
	margin-bottom: 10px;

	.bottom {
		margin-top: 10px;
	}

	a {
		text-decoration: none;
		color: hsl(206, 100%, 40%);
	}
`;
const SignupForm = () => {
	return (
		<SignupFormStyle>
			<InputForm text="Display name" />
			<InputForm text="Email" />
			<InputForm text="Password" />
			<Guide>
				<p>
					Passwords must contain at least eight characters, including at least
					1letter and 1 number.
				</p>
			</Guide>
			<Button text="Sign up" />
			<Guide>
				<p className="bottom">
					By clicking “Sign up”, you agree to our
					<a
						href="https://stackoverflow.com/legal/terms-of-service/public"
						target={'_blank'}
						rel="noreferrer">
						terms of service
					</a>
					,
					<a
						href="https://stackoverflow.com/legal/privacy-policy"
						target={'_blank'}
						rel="noreferrer">
						privacy policy
					</a>
					and
					<a
						href="https://stackoverflow.com/legal/cookie-policy"
						target={'_blank'}
						rel="noreferrer">
						cookie policy
					</a>
				</p>
			</Guide>
		</SignupFormStyle>
	);
};

export default SignupForm;
