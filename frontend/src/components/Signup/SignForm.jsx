import { useState } from 'react';
import styled from 'styled-components';
import { Signup } from '../../api/SignupApi';
import Button from '../common/Button';
import InputForm from '../common/InputForm';
import { useNavigate } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';
import Confirm from '../common/Confirm';

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
	const [signupInfo, setSignupInfo] = useState({
		displayName: '',
		email: '',
		password: '',
	});

	const navigate = useNavigate();

	const handleInputValue = (key) => (e) => {
		setSignupInfo({ ...signupInfo, [key]: e.target.value });
	};
	const signupRequestHandler = () => {
		const { displayName, email, password } = signupInfo;
		if (!displayName) {
			alert('Display Name을 입력하세요');
			return;
		} else if (!email) {
			alert('Email을 입력하세요');
			return;
		} else if (!password) {
			alert('Password를 입력하세요');
			return;
		}
		//api

		Signup(signupInfo).then((res) => {
			//결과 상태 result에 저장 후 200 = 회원가입 완료 , 400,500 = 오류가 발생했습니다 보여줄 예정
			//200 일경우 login page로 redirect
			// if (res.state === 200) {
			const title = '회원가입 완료';
			const contents = `로그인 화면으로 이동하시겠습니까?`;
			confirmAlert({
				customUI: ({ onClose }) => {
					return (
						<Confirm
							onClose={onClose}
							title={title}
							content={contents}
							callback={() => navigate('/login')}
						/>
					);
				},
			});
			// }
		});
	};

	return (
		<SignupFormStyle>
			<InputForm
				text="Display name"
				callback={handleInputValue('displayName')}
			/>
			<InputForm text="Email" callback={handleInputValue('email')} />
			<InputForm
				text="Password"
				type="password"
				callback={handleInputValue('password')}
			/>
			<Guide>
				<p>
					Passwords must contain at least eight characters, including at least
					1letter and 1 number.
				</p>
			</Guide>
			<Button text="Sign up" callback={signupRequestHandler} />
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
