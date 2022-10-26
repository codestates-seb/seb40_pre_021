import styled from 'styled-components';

const MypageAvatar = () => {
	return (
		<div>
			<Avatar src="https://via.placeholder.com/150" />
		</div>
	);
};

export default MypageAvatar;

const Avatar = styled.img`
	border-radius: 2px;
	width: 128px;
	height: 128px;
`;
