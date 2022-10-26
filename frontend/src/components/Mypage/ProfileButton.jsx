import styled from 'styled-components';

const ProfileButton = ({ text, icon }) => {
	return (
		<Button>
			<IconBox>{icon}</IconBox>
			{text}
		</Button>
	);
};

export default ProfileButton;

const Button = styled.a`
	display: flex;
	align-items: center;
	border: 1px solid #9fa6ad;
	border-radius: 3px;
	padding: 9.6px;
	margin: 3px;
	background-color: white;
	color: #6a737c;
	font-size: 0.8rem;
	font-weight: 600;
	cursor: pointer;
	:hover {
		color: #525960;
		background-color: #f8f9f9;
	}
`;

const IconBox = styled.div`
	margin-right: 2px;
`;
