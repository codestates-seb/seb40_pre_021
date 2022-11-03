import styled from 'styled-components';
import { GoCheck } from 'react-icons/go';

const ListAdditionalInfo = ({ vote, choosed, answerCount, views, type }) => {
	return (
		<Container>
			<VotesBox>
				<span>{vote}</span>
				<span>votes</span>
			</VotesBox>
			<AnswerBox choosed={choosed}>
				{choosed ? <GoCheck style={{ color: 'white' }} /> : null}
				{!type ? (
					<>
						<span>{answerCount}</span>
						<span>answer</span>
					</>
				) : (
					<span>Accepted</span>
				)}
			</AnswerBox>
			{views ? (
				<ViewsBox>
					<span>{views}</span>
					<span>views</span>
				</ViewsBox>
			) : null}
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
	font-size: 13px;
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
		font-weight: 400;
		font-size: 13px;
		&:first-child {
			font-weight: 500;
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
	gap: 0.2rem;
	align-items: center;
	justify-content: center;
	white-space: nowrap;

	span {
		color: ${(props) => (props.choosed ? 'white' : '#2E6F44')};
		font-weight: 500;
		&:last-child {
			font-weight: 400;
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
		font-weight: 400;
		&:first-child {
			font-weight: 500;
		}
	}
`;
