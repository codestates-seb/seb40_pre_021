import styled from 'styled-components';

const Avatar = ({ nickname }) => {
	return (
		<AvatarBox>
			<AvatarText>
				{nickname.length > 4 ? nickname.slice(0, 4) : nickname}
			</AvatarText>
		</AvatarBox>
	);
};

export default Avatar;

const AvatarBox = styled.div`
	background-color: #5c6bc0;
	width: 128px;
	height: 128px;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 5px;
	box-shadow: 0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
		0 2px 8px hsla(0, 0%, 0%, 0.05);
`;

const AvatarText = styled.span`
	color: white;
	font-size: 3rem;
`;
