import instance from './root';

export const getList = async (data) => {
	let url = '/questions';
	if (data.q !== undefined) {
		url = `/search/${encodeURI(data.q)}`;
		data = {};
	}
	try {
		//test
		// const result = await instance.get('/questions', { params: data });
		//real
		const result = await instance.get(url, { params: data });
		return result.data;
	} catch (err) {
		console.log(err);
	}
};

export const getHomeList = async (data) => {
	try {
		//test
		// const result = await instance.get('/questions', { params: data });
		//real
		const result = await instance.get('/', { params: data });
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
