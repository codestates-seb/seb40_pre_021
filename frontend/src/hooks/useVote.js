import { useState, useEffect } from 'react';

const useVoteStatus = (data, nickname) => {
	const [likeYn, setlikeYn] = useState(false);
	const [unlikeYn, setUnlikeYn] = useState(false);
	if (data) {
		useEffect(() => {
			const liked = data.filter((el) => el.nickname === nickname);
			if (liked.length !== 0 && liked[0].likeYn === true) setlikeYn(true);
			if (liked.length !== 0 && liked[0].unlikeYn === true) setUnlikeYn(true);
		}, [data]);
	}
	return [likeYn, unlikeYn];
};

export default useVoteStatus;
