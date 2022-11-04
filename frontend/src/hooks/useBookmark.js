import { useEffect, useState } from 'react';

const useBookmark = (data, nickname) => {
	const [isBookmarked, setIsBookmarked] = useState(false);
	useEffect(() => {
		if (data) {
			const bookmarked = data.filter((el) => el.nickname === nickname);
			if (bookmarked.length !== 0) setIsBookmarked(true);
			else setIsBookmarked(false);
		}
	}, [data]);
	return [isBookmarked];
};

export default useBookmark;
