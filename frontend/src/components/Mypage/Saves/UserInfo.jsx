import styled from 'styled-components';
import defaultImage from '../../../assets/images/userDefaultImage.png';

const UserInfo = ({ questionUser, date }) => {
	return (
		<Container>
			<img src={defaultImage} alt="user" />
			<a href="1">{questionUser}</a>
			<time>asked</time>
			<span>{date}</span>
		</Container>
	);
};

export default UserInfo;

const Container = styled.div`
	display: flex;
	justify-content: flex-end;
	align-items: center;
	gap: 4px;
	flex-wrap: wrap;
	margin-left: auto;
	grid-template-columns: auto 1fr;
	line-height: 1;

	img {
		border-radius: 3px;
		width: 16px;
		height: 16px;
	}

	a {
		margin: 2px;
		color: #0074cc;
		text-decoration: none;
		cursor: pointer;
		font-size: 12px;
		font-weight: 400;
		:hover {
			color: #0a95ff;
		}
	}

	time {
		color: #6a737c;
		font-size: 12px;
		font-weight: 400;
	}

	span {
		color: #6a737c;
		font-size: 12px;
		font-weight: 400;
	}
`;
