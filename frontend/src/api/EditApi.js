import instance from './root';

export const getThreadInfo = async () => {
	try {
		const result = await instance.get(`/question/:questionId/data`);
		// const result = await instance.get(`/data`); //테스트용
		return result.data;
	} catch (err) {
		return err;
	}
}; //질문 상세페이지 조회와 같음

export const edit = async () => {
	try {
		const result = await instance.put(`/question/:questionId`);
		// const result = await instance.get(`/data`); //테스트용
		return result.data;
	} catch (err) {
		return err;
	}
};
