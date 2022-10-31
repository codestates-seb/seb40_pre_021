import axios from 'axios';

const JWT_EXPIRY_TIME = 60 * 1000; // 만료 시간 (1분)

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

//acessToken reissue

//real /users/refresh
//test /signup
export const AccessTokenRefresh = async () => {
	try {
		const result = await instance.get(`/signup`);
		console.log('AccessTokenRefresh');
		if (result.data.accessToken) {
			TokenExpireSetting(result);
		}
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

//토큰 만료시 후 처리
export const TokenExpireSetting = (response) => {
	const { accessToken } = response.data;

	// accessToken 설정
	axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;

	// accessToken 만료하기 10초전에 로그인 연장
	setTimeout(AccessTokenRefresh, JWT_EXPIRY_TIME - 10000);
};
