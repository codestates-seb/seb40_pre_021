import axios from 'axios';
import instance from './root';

const JWT_EXPIRY_TIME = 60 * 60 * 1000; // 만료 시간 (1시간)

//acessToken reissue

export const AccessTokenRefresh = async () => {
	try {
		//test
		// const result = await instance.get(`/login`);
		//real
		const result = await instance.get(`/users/refresh`);
		if (result.data.accessToken) {
			TokenExpireSetting(result);
		}
		return result.data;
	} catch (err) {
		console.log(err);
		return err;
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
