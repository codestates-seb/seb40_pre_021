import MypageAvatar from '../components/Mypage/MypageAvatar';
import MypageUserNickname from '../components/Mypage/MypageUserNickname';
import UserSignupInfo from '../components/Mypage/UserSignupInfo';

const Mypage = () => {
	return (
		<div>
			<MypageAvatar />
			<MypageUserNickname />
			<UserSignupInfo value={4} />
		</div>
	);
};

export default Mypage;
