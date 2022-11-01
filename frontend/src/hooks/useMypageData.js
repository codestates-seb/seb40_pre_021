import { useEffect, useState } from 'react';
import {
	getMypageUserAnswer,
	getMypageUserBookmark,
	getMypageUserInfo,
	getMypageUserQuestion,
	getMypageUserTag,
} from '../api/mypageApi';

function useMypageData(category, value = []) {
	const [data, setData] = useState({ [category]: value });

	useEffect(() => {
		switch (category) {
			case 'answer':
				getMypageUserAnswer().then((res) => {
					setData({ ...data, [category]: res });
				});
				break;
			case 'question':
				getMypageUserQuestion().then((res) => {
					setData({ ...data, [category]: res });
				});
				break;
			case 'tag':
				getMypageUserTag().then((res) => {
					setData({ ...data, [category]: res });
				});
				break;
			case 'bookmark':
				getMypageUserBookmark().then((res) => {
					setData({ ...data, [category]: res });
				});
				break;
			case 'userInfo':
				getMypageUserInfo().then((res) => {
					setData({ ...data, [category]: res });
				});
				break;
		}
	}, [category]);

	return [data[category], setData];
}

export default useMypageData;
