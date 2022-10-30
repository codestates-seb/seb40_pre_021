import axios from 'axios';

const axiosConfig = {
	baseURL: 'http://localhost:3001',
};

const instance = axios.create(axiosConfig);

//dev : login
//real : users/login
export const Login = async (data) => {
	try {
		const result = await instance.post(
			`/login
		`,
			data,
		);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
