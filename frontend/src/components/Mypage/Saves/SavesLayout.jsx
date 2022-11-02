import styled from 'styled-components';
import Button from '../../common/Button';
import SortButtonGroup from '../Activity/SortButtonGroup';
import EmptySavesBox from './EmptySavesBox';
import SavesListBox from './SavesListBox';

let sortData = [
	{
		id: 0,
		name: 'Score',
		clicked: true,
	},
	{
		id: 1,
		name: 'Activity',
		clicked: false,
	},
	{
		id: 2,
		name: 'Views',
		clicked: false,
	},
	{
		id: 3,
		name: 'Newest',
		clicked: false,
	},
	{
		id: 4,
		name: 'Added',
		clicked: false,
	},
];

const SavesLayout = ({ bookmark }) => {
	const total = bookmark?.length;
	return (
		<Container>
			<TopWrapper>
				<ListHeader>All saves</ListHeader>
				<Button text="Create new list" />
			</TopWrapper>
			<InfoArea>
				<CountText>{total} Saved items</CountText>
				{bookmark.length ? <SortButtonGroup data={sortData} /> : null}
			</InfoArea>
			{bookmark.length ? (
				<SavesListBox bookmarks={bookmark} />
			) : (
				<EmptySavesBox />
			)}
		</Container>
	);
};

export default SavesLayout;

const Container = styled.div`
	flex-grow: 1;
	flex-basis: 83.3%;
`;

const TopWrapper = styled.div`
	display: flex;
	margin-bottom: 10px;
	justify-content: space-between;
`;

const ListHeader = styled.h1`
	font-size: 21px;
	line-height: 1.3;
	font-weight: 400;
`;

const InfoArea = styled(TopWrapper)`
	margin-bottom: 0.5rem;
	align-items: center;
`;

const CountText = styled.h2`
	font-size: 19px;
	margin: 0 0 1rem;
	font-weight: 400;
`;
