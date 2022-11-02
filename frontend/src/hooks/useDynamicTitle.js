import { useEffect, useState } from 'react';
import { getMypageUserInfo } from '../api/mypageApi';

const useDynamicTitle = (title, user = false) => {
	const [nickname, setNickname] = useState('');
	useEffect(() => {
		if (user) {
			getMypageUserInfo().then((res) => {
				setNickname(res.nickname);
			});
		}
	}, [user]);

	useEffect(() => {
		if (nickname) {
			document.title = `${title} ${nickname} - Stack Overflow`;
		} else {
			document.title = `${title}`;
		}
		return () => {
			document.title = 'Stack Overflow';
		};
	}, [nickname]);
};

export default useDynamicTitle;
