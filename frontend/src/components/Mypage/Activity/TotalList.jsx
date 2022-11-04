import { useEffect, useState } from 'react';
import styled from 'styled-components';
import ListAdditionalInfo from '../common/ListAdditionalInfo';
import TagList from '../common/TagList';
import UserInfo from '../common/UserInfo';
import SortButtonGroup from './SortButtonGroup';
import Title from './Title';

let questionSortData = [
	{
		id: 0,
		name: 'Score',
		clicked: true,
	},
	{
		id: 1,
		name: 'Newest',
		clicked: false,
	},
	{
		id: 2,
		name: 'Views',
		clicked: false,
	},
];

let answerSortData = [
	{
		id: 0,
		name: 'Score',
		clicked: true,
	},
	{
		id: 1,
		name: 'Newest',
		clicked: false,
	},
];

const TotalList = ({ lists, title, type, handleSortLists, callback }) => {
	const [sortData, setSortData] = useState([]);
	console.log(sortData);

	useEffect(() => {
		type === 'answer'
			? setSortData(answerSortData)
			: setSortData(questionSortData);
	}, [type]);
	return (
		<Container>
			<TitleBox>
				<Title title={title} number={lists?.length} />
				<SortButtonGroup
					menus={sortData}
					handleSortLists={handleSortLists}
					data={lists}
					callback={callback}
				/>
			</TitleBox>
			<ListContainer>
				{lists?.map((list) => {
					const {
						id,
						answerCount,
						choosed,
						createdAt,
						tags,
						title,
						url,
						views,
						vote,
					} = list;

					let splitDate = new Date(createdAt).toString().split(' ');
					let date = `${splitDate[1]} ${splitDate[2]}, ${splitDate[3]} at ${splitDate[4]}`;
					return (
						<ListBox key={id}>
							<ListAdditionalInfo
								vote={vote}
								choosed={choosed}
								answerCount={answerCount}
								views={views}
								type={type}
							/>
							<ContentBox>
								<h3>
									<a href={url}>{title}</a>
								</h3>
								<TagAndUserInfoBox>
									{tags ? <TagList tag={tags} /> : <div></div>}

									<UserInfo date={date} />
								</TagAndUserInfoBox>
							</ContentBox>
						</ListBox>
					);
				})}
			</ListContainer>
		</Container>
	);
};

export default TotalList;

const Container = styled.div`
	&&& {
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: column;
	}
`;

const TitleBox = styled.div`
	display: flex;
	margin-bottom: 8px;
	align-items: flex-end;
	flex-wrap: wrap;
	justify-content: space-between;
`;

const ListContainer = styled.div`
	border: 1px solid #e4e6e8;
	border-radius: 5px;
`;

const ListBox = styled.div`
	display: flex;
	flex-direction: column;
	border-bottom: 1px solid #e4e6e8;
	padding: 16px;

	&:last-child {
		border: none;
	}
`;

const ContentBox = styled.div`
	width: 100%;
	flex-grow: 1;
	min-width: 100%;

	h3 {
		font-size: 1.05rem;
		margin-top: -0.15rem;
		margin-bottom: 0.38rem;
		padding-right: 24px;
		font-weight: 400;
		word-break: break-word;
		overflow-wrap: break-word;
		hyphens: auto;
		a {
			text-decoration: none;
			cursor: pointer;
			color: #0074cc;
			:hover {
				color: #0a95ff;
			}
		}
	}
`;

const TagAndUserInfoBox = styled.div`
	display: flex;
	align-items: center;
	justify-content: space-between;
	flex-wrap: wrap;
	column-gap: 6px;
	row-gap: 8px;
`;
