import { useState } from 'react';
import styled from 'styled-components';
import { Login } from '../../api/LoginApi';
import Button from '../common/Button';
import InputForm from '../common/InputForm';
import { useDispatch } from 'react-redux'; // 1, 2.
import { loginSuccess } from '../../modules/userReducer';
import { useNavigate } from 'react-router-dom';

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
	const [loginInfo, setLoginInfo] = useState({
		email: '',
		password: '',
	});

	const dispatch = useDispatch();
	const navigate = useNavigate();
	const handleInputValue = (key) => (e) => {
		setLoginInfo({ ...loginInfo, [key]: e.target.value });
	};
	const loginRequestHandler = () => {
		const { email, password } = loginInfo;
		if (!email || !password) {
			alert('Email과 Password를 입력하세요');
			return;
		}
		//api
		Login(loginInfo).then((res) => {
			//응답이 정상이 아닌경우
			if (res.code) {
				alert(res.message);
			} else {
				//redux-toolkit
				dispatch(loginSuccess(res));

				// 값 꺼낼때는
				// const isLogin = useSelector(selectIsLogin);
				// const userInfo = useSelector(selectUserInfo);
				// ../Header/RightMenu.jsx 에 예시있음

				//store에 저장완료 후 메인페이지로 이동
				return navigate('/', { replace: true });
			}
		});
	};
	return (
		<LoginFormStyle>
			<InputForm text="Email" callback={handleInputValue('email')} />
			<InputForm
				text="Password"
				blueText="Forgot password?"
				type="password"
				callback={handleInputValue('password')}
			/>
			<Button text="Log in" callback={loginRequestHandler} />
		</LoginFormStyle>
	);
};

export default LoginForm;
