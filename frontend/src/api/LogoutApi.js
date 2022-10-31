import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

//test
//test logout은 아무 의미 없음 통신 후처리를 위해 만듬
export const Logout = async (data) => {
	try {
		const result = await instance.get(`/signup`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

//real
// export const Logout = async (data) => {
// 	try {
// 		const result = await instance.delete(
// 			`/users/logout
// 		`,
// 			data,
// 		);
// 		return result.data;
// 	} catch (err) {
// 		console.log(err);
// 	}
// };
