import styled from 'styled-components';

const UserNickname = ({ nickname }) => {
	return <Nickname>{nickname}</Nickname>;
};

export default UserNickname;

const Nickname = styled.span`
	color: #232629;
	margin: 4px 4px 12px 0px;
	font-size: 40px;
	font-weight: 500;
`;
