import axios from 'axios';
import { root } from './root';

const JWT_EXPIRY_TIME = 60 * 1000; // 만료 시간 (1분)

const axiosConfig = {
	baseURL: root,
};

const instance = axios.create(axiosConfig);

//acessToken reissue

export const AccessTokenRefresh = async () => {
	try {
		//test
		// const result = await instance.get(`/login`);
		//real
		console.log('AccessTokenRefresh start');
		const result = await instance.get(`/users/refresh`);
		console.log('AccessTokenRefresh', result);
		if (result.data.accessToken) {
			console.log('AccessTokenRefresh');
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
