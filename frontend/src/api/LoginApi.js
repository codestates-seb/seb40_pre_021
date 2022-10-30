import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

//test
//test를 위해 회원가입 데이터를 get 하여 들고옴
export const Login = async (data) => {
	try {
		const result = await instance.get(`/signup`);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

//real
// export const Login = async (data) => {
// 	try {
// 		const result = await instance.post(
// 			`/users/login
// 		`,
// 			data,
// 		);
// 		return result.data;
// 	} catch (err) {
// 		console.log(err);
// 	}
// };
