import instance from './root';

export const getList = async (data) => {
	let url = '/questions';
	if (data.q !== undefined) {
		url = '/search';
	}
	// console.log(url, data);
	try {
		//test
		// const result = await instance.get('/questions', { params: data });
		//real
		console.log('getList start');
		const result = await instance.get(url, { params: data });
		console.log('getList', result);
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
		console.log('getHomeList start');
		const result = await instance.get('/', { params: data });
		console.log('getHomeList', result);
		return result.data;
	} catch (err) {
		console.log(err);
	}
};
