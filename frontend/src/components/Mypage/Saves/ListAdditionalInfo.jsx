import styled from 'styled-components';
import { GoCheck } from 'react-icons/go';

const ListAdditionalInfo = ({ vote, choosed, answerCount, views }) => {
	return (
		<Container>
			<VotesBox>
				<span>{vote}</span>
				<span>votes</span>
			</VotesBox>
			<AnswerBox choosed={choosed}>
				{choosed ? <GoCheck style={{ color: 'white' }} /> : null}
				<span>{answerCount}</span>
				<span>answer</span>
			</AnswerBox>
			<ViewsBox>
				<span>{views}</span>
				<span>views</span>
			</ViewsBox>
		</Container>
	);
};

export default ListAdditionalInfo;

const Container = styled.div`
	width: auto;
	flex-direction: row;
	align-items: center;
	gap: 6px;
	margin-right: 16px;
	margin: 4px 4px 8px 0;
	display: flex;
	flex-shrink: 0;
	flex-wrap: wrap;
	font-size: 14px;
	color: #6a737c;
`;

const VotesBox = styled.div`
	color: #0c0d0e;
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;
	border: 1px solid transparent;

	span {
		font-weight: 600;
		font-size: 14px;
		&:first-child {
			font-weight: 700;
		}
	}
`;

const AnswerBox = styled.div`
	color: ${(props) => (props.choosed ? '#2E6F44' : 'white')};
	background-color: ${(props) => (props.choosed ? '#2E6F44' : 'white')};
	border: 1px solid #2e6f44;
	border-radius: 3px;
	padding: 4px;
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;

	span {
		color: ${(props) => (props.choosed ? 'white' : '#2E6F44')};
		font-weight: 600;
		&:first-child {
			font-weight: 700;
		}
	}
`;

const ViewsBox = styled.div`
	display: inline-flex;
	gap: 0.3rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;
	border: 1px solid transparent;

	span {
		font-weight: 600;
		&:first-child {
			font-weight: 700;
		}
	}
`;
