import { useEffect, useState } from 'react';
import {
	getMypageUserAnswer,
	getMypageUserBookmark,
	getMypageUserInfo,
	getMypageUserQuestion,
	getMypageUserTag,
} from '../api/mypageApi';

function useMypageData(category, value = []) {
	const [data, setData] = useState(value);

	useEffect(() => {
		switch (category) {
			case 'answer':
				getMypageUserAnswer().then((res) => {
					let sortData = res.sort((a, b) => b.vote - a.vote);
					setData(sortData);
				});
				break;
			case 'question':
				getMypageUserQuestion().then((res) => {
					let sortData = res.sort((a, b) => b.vote - a.vote);
					setData(sortData);
				});
				break;
			case 'tag':
				getMypageUserTag().then((res) => {
					let sortData = res.sort((a, b) => b.tagCount - a.tagCount);
					setData(sortData);
				});
				break;
			case 'bookmark':
				getMypageUserBookmark().then((res) => {
					setData(res);
				});
				break;
			case 'userInfo':
				getMypageUserInfo().then((res) => {
					setData(res);
				});
				break;
		}
	}, [category]);

	return [data, setData];
}

export default useMypageData;
