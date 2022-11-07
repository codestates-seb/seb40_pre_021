import instance from './root';

//dev : signup
//real : users/signup
//test : data.json 에 "signup": {} 추가
export const Signup = async (data) => {
	try {
		//test용
		// data.accessToken = 'dd';
		// const result = await instance.post(`/signup`, data);
		//real
		const result = await instance.post(`/users/signup`, data);
		return result.data;
	} catch (err) {
		return err;
	}
};
