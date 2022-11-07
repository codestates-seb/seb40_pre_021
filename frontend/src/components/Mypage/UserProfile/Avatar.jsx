import styled from 'styled-components';

const Avatar = ({ nickname, ...props }) => {
	return (
		<AvatarBox {...props}>
			<AvatarText {...props}>
				{nickname?.length > 3 ? nickname.slice(0, 3) : nickname}
			</AvatarText>
		</AvatarBox>
	);
};

export default Avatar;

const AvatarBox = styled.div`
	background-color: #5c6bc0;
	width: ${(props) => props.width || `128px`};
	height: ${(props) => props.heigth || `128px`};
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 5px;
	box-shadow: ${(props) =>
		props.shadow
			? `0 1px 2px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.05),
		0 2px 8px hsla(0, 0%, 0%, 0.05)`
			: ''};
`;

const AvatarText = styled.span`
	color: white;
	font-size: ${(props) => props.fontSize || `3rem`};
	padding: ${(props) => props.padding || ``};
`;
